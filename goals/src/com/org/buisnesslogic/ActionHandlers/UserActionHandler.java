package com.org.buisnesslogic.ActionHandlers;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import com.org.coursegenrator.utilities.Constants;
import com.org.sg.DAO.AdaptationknowledgeDAO;
import com.org.sg.DAO.AssociatedProjectsDAO;
import com.org.sg.DAO.CPropertyDAO;
import com.org.sg.DAO.ConceptDAO;
import com.org.sg.DAO.ConceptPrDAO;
import com.org.sg.DAO.ConceptrelationDAO;
import com.org.sg.DAO.GameresourceDAO;
import com.org.sg.DAO.GrPropertyDAO;
import com.org.sg.DAO.PedagogicalGameDAO;
import com.org.sg.DAO.PedagogicalresourceDAO;
import com.org.sg.DAO.PrPropertyDAO;
import com.org.sg.DAO.PresentationDAO;
import com.org.sg.DAO.PresentationPrDAO;
import com.org.sg.DAO.ProjectDAO;
import com.org.sg.DAO.UserDAO;
import com.org.sg.POJO.action.Adaptationknowledge;
import com.org.sg.POJO.action.AssociatedProjects;
import com.org.sg.POJO.action.CProperty;
import com.org.sg.POJO.action.Concept;
import com.org.sg.POJO.action.ConceptPr;
import com.org.sg.POJO.action.Conceptrelation;
import com.org.sg.POJO.action.Gameresource;
import com.org.sg.POJO.action.GrProperty;
import com.org.sg.POJO.action.PedagogicalGame;
import com.org.sg.POJO.action.Pedagogicalresource;
import com.org.sg.POJO.action.PrProperty;
import com.org.sg.POJO.action.Presentation;
import com.org.sg.POJO.action.PresentationPr;
import com.org.sg.POJO.action.Project;
import com.org.sg.POJO.action.User;

public class UserActionHandler extends AbstractActionHandler {

	private User user;

	public UserActionHandler(User user) {
		super();
		this.user = user;
	}

	@Override
	public void processing() {
		if (user.getAction().equalsIgnoreCase("login"))
			checkLogin();
		else if (Constants.LOGOUT.equalsIgnoreCase(user.getAction()))
			logout();
		else if (Constants.SHOW.equalsIgnoreCase(user.getAction()))
			show();
		else if (Constants.SHOW_ADD_EDIT.equalsIgnoreCase(user.getAction()))
			showAddEdit();
		else if (Constants.ADD_EDIT.equalsIgnoreCase(user.getAction()))
			addEdit();
		else if (Constants.DELETE.equalsIgnoreCase(user.getAction()))
			delete();
	}

	public void logout() {

	}

	public void show() {
		UserDAO userDAO = new UserDAO();
		this.user.setUserList(userDAO.findUsers((Integer) user.getSession().get(Constants.LOGIN_ID)));

	}

	public void showAddEdit() {

		AssociatedProjectsDAO associatedProjectsDAO = new AssociatedProjectsDAO();
		ProjectDAO projectDAO = new ProjectDAO();

		Integer adminId = (Integer) user.getSession().get(Constants.LOGIN_ID);

		if (user.getId() != null && !user.getId().equals(0)) {
			UserDAO userDAO = new UserDAO();

			User l = userDAO.findById(user.getId());
			user.setName(l.getName().replaceAll("'", "\'"));
			user.setEmail(l.getEmail());
			user.setOrganization(l.getOrganization());
			user.setLogin(l.getLogin());
			user.setPassword("");
			user.setId(l.getId());

			List<AssociatedProjects> list = associatedProjectsDAO.findAssociatedProjects(adminId, user.getId());
			Integer[] a = new Integer[list.size()];
			int count = 0;
			for (AssociatedProjects lp : list) {
				a[count++] = lp.getProjectByAdminProject().getId();
			}

			user.setProjectIds(a);
			user.setResetPassword(true);
			user.setSendByEmail(true);
		} else {
			user.setResetPassword(false);
			user.setSendByEmail(false);
		}

		user.setProjectList(projectDAO.findProjectsOfUser(adminId));
	}

	public void addEdit() {
		UserDAO userDAO = new UserDAO();
		AssociatedProjectsDAO associatedProjectsDAO = new AssociatedProjectsDAO();
		ProjectDAO projectDAO = new ProjectDAO();

		Map<Integer, Concept> conceptMap = new LinkedHashMap<Integer, Concept>();
		Map<Integer, Pedagogicalresource> prMap = new LinkedHashMap<Integer, Pedagogicalresource>();
		Map<Integer, Gameresource> grMap = new LinkedHashMap<Integer, Gameresource>();
		User l;
		Integer adminId = (Integer) user.getSession().get(Constants.LOGIN_ID);

		if (user.getId() != null && !user.getId().equals(0)) {
			l = userDAO.findById(user.getId());
			user.setNewObject(false);
		} else {
			l = new User();
			l.setType(Constants.TEACHER_TYPE);
			user.setNewObject(true);
		}

		l.setName(user.getName());
		l.setEmail(user.getEmail());
		l.setOrganization(user.getOrganization());
		l.setLogin(user.getLogin());

		if (user.getResetPassword() || l.getNewObject()) {
			l.setPassword(Constants.encryptPassword(user.getPassword()));
		}

		userDAO.save(l);

		if (user.getProjectIds() != null) {
			User admin = userDAO.findById(adminId);

			List<AssociatedProjects> associatedProjectList = associatedProjectsDAO.findAssociatedProjects(adminId, l.getId());
			Map<Integer, AssociatedProjects> apList = new LinkedHashMap<Integer, AssociatedProjects>();

			for (AssociatedProjects ap : associatedProjectList) {
				apList.put(ap.getProjectByAdminProject().getId(), ap);
			}

			for (Integer lpId : user.getProjectIds()) {

				AssociatedProjects associatedProjects = new AssociatedProjects();

				if (apList.containsKey(lpId)) {
					associatedProjects = apList.get(lpId);
					if (associatedProjects.getModified() != null && associatedProjects.getModified()) {
						continue;
					}
					
					associatedProjectsDAO.delete(associatedProjects);
					projectDAO.delete(associatedProjects.getProjectByTeacherProject());
					
				}

				associatedProjects = new AssociatedProjects();

				/*
				 * Begin duplication of projects
				 */

				Project newProject = new Project();
				Project project = projectDAO.findById(lpId);

				newProject.setName(project.getName());
				newProject.setDescription(project.getDescription());
				newProject.setDateCreation(new Date());
				newProject.setUser(l);

				projectDAO.save(newProject);

				/*
				 * Set up concepts, pedagogical resources and game resources
				 * along with their properties
				 */

				newProject.setConcepts(copyProjectConcepts(project, newProject, conceptMap));
				newProject.setPedagogicalresources(copyProjectPRs(project, newProject, prMap));
				newProject.setGameresources(copyProjectGRs(project, newProject, grMap));

				/*
				 * Next set up the relations of concepts, pedagogical resources
				 * and game resources
				 */

				copyConceptRelations(project.getConcepts(), conceptMap);
				copyConceptPrRelations(project.getPedagogicalresources(), prMap, conceptMap);
				copyPrGrRelations(project.getPedagogicalresources(), prMap, grMap);

				/*
				 * Copy presentations and adaptation knowledges
				 */

				copyProjectAKs(project, newProject, prMap);
				copyProjectPresentations(project, newProject);
				/*
				 * End Duplication of projects
				 */

				associatedProjects.setDatecreated(new Date());
				associatedProjects.setUserByAdmin(admin);
				associatedProjects.setUserByTeacher(l);
				associatedProjects.setProjectByAdminProject(project);
				associatedProjects.setProjectByTeacherProject(newProject);

				associatedProjectsDAO.save(associatedProjects);

			}
		}

		user.setId(l.getId());
	}

	private void copyProjectAKs(Project oldProject, Project newProject, Map<Integer, Pedagogicalresource> prMap) {
		AdaptationknowledgeDAO adaptationknowledgeDAO = new AdaptationknowledgeDAO();

		for (Adaptationknowledge ak : oldProject.getAdaptationknowledges()) {
			Adaptationknowledge adaptationknowledge = new Adaptationknowledge();
			adaptationknowledge.setDescription(ak.getDescription());
			adaptationknowledge.setPedagogicalresource(prMap.get(ak.getPedagogicalresource().getId()));
			adaptationknowledge.setProject(newProject);
			adaptationknowledge.setRule(ak.getRule());
			adaptationknowledge.setSortOrder(ak.getSortOrder());

			adaptationknowledgeDAO.save(adaptationknowledge);
		}

	}

	private void copyProjectPresentations(Project oldProject, Project newProject) {
		PresentationDAO presentationDAO = new PresentationDAO();
		PresentationPrDAO presentationPrDAO = new PresentationPrDAO();

		for (Presentation pre : oldProject.getPresentations()) {
			Presentation presentation = new Presentation();
			presentation.setProject(newProject);
			presentation.setName(pre.getName());
			presentation.setDescription(pre.getDescription());

			presentationDAO.save(presentation);

			for (PresentationPr pr : pre.getPresentationPrs()) {
				PresentationPr presentationPr = new PresentationPr();
				presentationPr.setDescription(pr.getDescription());
				presentationPr.setPresentation(presentation);
				presentationPr.setTypes(pr.getTypes());

				presentationPrDAO.save(presentationPr);
			}
		}

	}

	private Set<Concept> copyProjectConcepts(Project oldProject, Project newProject, Map<Integer, Concept> conceptMap) {
		ConceptDAO conceptDAO = new ConceptDAO();
		CPropertyDAO cPropertyDAO = new CPropertyDAO();
		Set<Concept> concepts = new HashSet<Concept>();

		for (Concept con : oldProject.getConcepts()) {
			Concept newConcept = new Concept();

			newConcept.setName(con.getName());
			newConcept.setDescription(con.getDescription());
			newConcept.setProject(newProject);

			conceptDAO.save(newConcept);

			concepts.add(newConcept);

			conceptMap.put(con.getId(), newConcept);

			for (CProperty cPr : con.getCProperties()) {
				CProperty cProperty = new CProperty();
				cProperty.setConcept(newConcept);
				cProperty.setName(cPr.getName());
				cProperty.setValue(cPr.getValue());

				cPropertyDAO.save(cProperty);
			}
		}
		return concepts;
	}

	private void copyConceptRelations(Set<Concept> concepts, Map<Integer, Concept> conceptMap) {
		ConceptrelationDAO conceptrelationDAO = new ConceptrelationDAO();
		for (Concept concept : concepts) {
			for (Conceptrelation cpr : concept.getConceptrelationsForConceptFrom()) {
				Conceptrelation conceptrelation = new Conceptrelation();
				conceptrelation.setConceptByConceptFrom(conceptMap.get(cpr.getConceptByConceptFrom().getId()));
				conceptrelation.setConceptByConceptTo(conceptMap.get(cpr.getConceptByConceptTo().getId()));
				conceptrelation.setFunctions(cpr.getFunctions());
				conceptrelation.setValue(cpr.getValue());
				conceptrelation.setRelation(cpr.getRelation());

				conceptrelationDAO.save(conceptrelation);
			}
		}
	}

	private Set<Pedagogicalresource> copyProjectPRs(Project oldProject, Project newProject, Map<Integer, Pedagogicalresource> prMap) {
		PedagogicalresourceDAO prDAO = new PedagogicalresourceDAO();
		PrPropertyDAO prPropertyDAO = new PrPropertyDAO();
		Set<Pedagogicalresource> prs = new HashSet<Pedagogicalresource>();

		for (Pedagogicalresource con : oldProject.getPedagogicalresources()) {
			Pedagogicalresource newPedagogicalResource = new Pedagogicalresource();

			newPedagogicalResource.setName(con.getName());
			newPedagogicalResource.setDescription(con.getDescription());
			newPedagogicalResource.setTypes(con.getTypes());
			newPedagogicalResource.setProject(newProject);

			prDAO.save(newPedagogicalResource);

			prs.add(newPedagogicalResource);

			prMap.put(con.getId(), newPedagogicalResource);

			for (PrProperty cPr : con.getPrProperties()) {
				PrProperty cProperty = new PrProperty();
				cProperty.setPedagogicalresource(newPedagogicalResource);
				cProperty.setName(cPr.getName());
				cProperty.setValue(cPr.getValue());

				prPropertyDAO.save(cProperty);
			}
		}
		return prs;
	}

	private void copyConceptPrRelations(Set<Pedagogicalresource> prs, Map<Integer, Pedagogicalresource> prMap, Map<Integer, Concept> conceptMap) {
		ConceptPrDAO conceptPrDAO = new ConceptPrDAO();
		for (Pedagogicalresource pr : prs) {
			for (ConceptPr cpr : pr.getConceptPrs()) {
				ConceptPr conceptPr = new ConceptPr();
				conceptPr.setPedagogicalresource(prMap.get(cpr.getPedagogicalresource().getId()));
				conceptPr.setConcept(conceptMap.get(cpr.getConcept().getId()));
				conceptPr.setRequiredKnowledge(cpr.getRequiredKnowledge());
				conceptPr.setValue(cpr.getValue());

				conceptPrDAO.save(conceptPr);
			}
		}
	}

	private Set<Gameresource> copyProjectGRs(Project oldGroject, Project newProject, Map<Integer, Gameresource> prMap) {
		GameresourceDAO prDAO = new GameresourceDAO();
		GrPropertyDAO prGropertyDAO = new GrPropertyDAO();
		Set<Gameresource> prs = new HashSet<Gameresource>();

		for (Gameresource con : oldGroject.getGameresources()) {
			Gameresource newGameResource = new Gameresource();

			newGameResource.setName(con.getName());
			newGameResource.setValue(con.getValue());
			newGameResource.setTypes(con.getTypes());
			newGameResource.setProject(newProject);

			prDAO.save(newGameResource);

			prs.add(newGameResource);

			prMap.put(con.getId(), newGameResource);

			for (GrProperty cGr : con.getGrProperties()) {
				GrProperty cGroperty = new GrProperty();
				cGroperty.setGameresource(newGameResource);
				cGroperty.setName(cGr.getName());
				cGroperty.setValue(cGr.getValue());

				prGropertyDAO.save(cGroperty);
			}
		}
		return prs;
	}

	private void copyPrGrRelations(Set<Pedagogicalresource> prs, Map<Integer, Pedagogicalresource> prMap, Map<Integer, Gameresource> grMap) {
		PedagogicalGameDAO pedagogicalGameDAO = new PedagogicalGameDAO();
		for (Pedagogicalresource pr : prs) {
			for (PedagogicalGame cpr : pr.getPedagogicalGames()) {
				PedagogicalGame pedagogicalGame = new PedagogicalGame();
				pedagogicalGame.setPedagogicalresource(prMap.get(cpr.getPedagogicalresource().getId()));
				pedagogicalGame.setGameresource(grMap.get(cpr.getGameresource().getId()));
				pedagogicalGame.setValue(cpr.getValue());

				pedagogicalGameDAO.save(pedagogicalGame);
			}
		}
	}

	public void checkLogin() {
		if (user.getLogin().trim().equals("") || user.getPassword().trim().equals("")) {
			user.getErrors().add(Constants.EMPTY_LOGIN_ERROR);
			user.setAction(Constants.LOGIN);
			return;
		}

		UserDAO userDAO = new UserDAO();
		User tempUser = userDAO.findByLogin(user.getLogin());

		if (tempUser == null) {
			user.getErrors().add(Constants.INVALID_USERNAME_ERROR);
			user.setAction(Constants.LOGIN);
			return;
		}

		ConfigurablePasswordEncryptor passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm("SHA-1");
		passwordEncryptor.setPlainDigest(true);

		if (!passwordEncryptor.checkPassword(Constants.ENCODE_STRING + user.getPassword(), tempUser.getPassword())) {
			user.getErrors().add(Constants.INVALID_USERNAME_PASSWORD_ERROR);
			user.setAction(Constants.LOGIN);
			return;
		}

		user.getSession().put(Constants.LOGIN, tempUser.getLogin());
		user.getSession().put(Constants.LOGIN_ID, tempUser.getId());
		user.getSession().put(Constants.LOGIN_NAME, tempUser.getName());
		user.getSession().put(Constants.LOGIN_TYPE, tempUser.getType());
		user.getSession().put(Constants.LOGIN_LEARNER, tempUser.getLearner());

		if (tempUser.getType().equals(Constants.ADMIN_TYPE))
			user.setAction(Constants.LOGGED_ADMIN);
		else if (tempUser.getType().equals(Constants.TEACHER_TYPE))
			user.setAction(Constants.LOGGED);
		else if (tempUser.getType().equals(Constants.STUDENT_TYPE))
			user.setAction(Constants.LOGGED_STUDENT);

	}

	public void delete() {
		UserDAO userDAO = new UserDAO();
		AssociatedProjectsDAO associatedProjectsDAO = new AssociatedProjectsDAO();

		associatedProjectsDAO.deleteOfUser(user.getId());

		userDAO.delete(userDAO.findById(user.getId()));
	}
}
