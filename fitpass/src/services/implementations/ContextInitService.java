package services.implementations;

import javax.servlet.ServletContext;

import beans.models.Administrator;
import beans.models.Buyer;
import beans.models.BuyerType;
import beans.models.Coach;
import beans.models.FacilityContent;
import beans.models.Guestbook;
import beans.models.Manager;
import beans.models.Membership;
import beans.models.MembershipType;
import beans.models.PromoCode;
import beans.models.SportsFacility;
import beans.models.SportsFacilityType;
import beans.models.Training;
import beans.models.TrainingHistory;
import beans.models.TrainingType;
import daos.implementations.AdministratorDAO;
import daos.implementations.BuyerDAO;
import daos.implementations.BuyerTypeDAO;
import daos.implementations.CoachDAO;
import daos.implementations.FacilityContentDAO;
import daos.implementations.GuestbookDAO;
import daos.implementations.ManagerDAO;
import daos.implementations.MembershipDAO;
import daos.implementations.MembershipTypeDAO;
import daos.implementations.PromoCodeDAO;
import daos.implementations.SportsFacilityDAO;
import daos.implementations.SportsFacilityTypeDAO;
import daos.implementations.TrainingDAO;
import daos.implementations.TrainingHistoryDAO;
import daos.implementations.TrainingTypeDAO;
import daos.interfaces.IDAO;
import repositories.implementations.AdministratorRepository;
import repositories.implementations.BuyerRepository;
import repositories.implementations.BuyerTypeRepository;
import repositories.implementations.CoachRepository;
import repositories.implementations.FacilityContentRepository;
import repositories.implementations.GuestbookRepository;
import repositories.implementations.ManagerRepository;
import repositories.implementations.MembershipRepository;
import repositories.implementations.MembershipTypeRepository;
import repositories.implementations.PromoCodeRepository;
import repositories.implementations.SportsFacilityRepository;
import repositories.implementations.SportsFacilityTypeRepository;
import repositories.implementations.TrainingHistoryRepository;
import repositories.implementations.TrainingRepository;
import repositories.implementations.TrainingTypeRepository;
import repositories.interfaces.IRepository;

public class ContextInitService {
	
	public static void initTrainingHistoryService(ServletContext ctx) {
		if (ctx.getAttribute("TrainingHistoryService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<TrainingHistory> trainingHistoryRepository = new TrainingHistoryRepository(contextPath);
	    	
	    	IDAO<TrainingHistory> trainingHistoryDAO = new TrainingHistoryDAO(trainingHistoryRepository);
	    	
			ctx.setAttribute("TrainingHistoryService", new TrainingHistoryService(trainingHistoryDAO));
		}
	}
	
	public static void initGuesbookService(ServletContext ctx) {
		if (ctx.getAttribute("GuestbookService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<Guestbook> guestbookRepository = new GuestbookRepository(contextPath);
	    	
	    	IDAO<Guestbook> guestbookDAO = new GuestbookDAO(guestbookRepository);
	    	
			ctx.setAttribute("GuestbookService", new GuestbookService(guestbookDAO));
		}
	}
	
	public static void initAdministratorService(ServletContext ctx) {
		if (ctx.getAttribute("AdministratorService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<Administrator> buyerRepository = new AdministratorRepository(contextPath);
	    	
	    	IDAO<Administrator> administratorDAO = new AdministratorDAO(buyerRepository);
	    	
			ctx.setAttribute("AdministratorService", new AdministratorService(administratorDAO));
		}
	}
	
	public static void initBuyerService(ServletContext ctx) {
		if (ctx.getAttribute("BuyerService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<Buyer> buyerRepository = new BuyerRepository(contextPath);
	    	
	    	IDAO<Buyer> buyerDAO = new BuyerDAO(buyerRepository);
	    	
			ctx.setAttribute("BuyerService", new BuyerService(buyerDAO));
		}
	}
	
	public static void initCoachService(ServletContext ctx) {
		if (ctx.getAttribute("CoachService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<Coach> coachRepository = new CoachRepository(contextPath);
	    	
	    	IDAO<Coach> coachDAO = new CoachDAO(coachRepository);
	    	
			ctx.setAttribute("CoachService", new CoachService(coachDAO));
		}
	}

	public static void initManagerService(ServletContext ctx) {
		if (ctx.getAttribute("ManagerService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<Manager> managerRepository = new ManagerRepository(contextPath);
	    	
	    	IDAO<Manager> managerDAO = new ManagerDAO(managerRepository);
	    	
			ctx.setAttribute("ManagerService", new ManagerService(managerDAO));
		}
	}
	
	public static void initRegisterService(ServletContext ctx) {
		if (ctx.getAttribute("RegisterService") == null) {
			ctx.setAttribute("RegisterService", new RegisterService());
		}
	}

	public static void initLoginService(ServletContext ctx) {
		if (ctx.getAttribute("LoginService") == null) {
			ctx.setAttribute("LoginService", new LoginService());
		}
	}
	public static void initSportsFacilityService(ServletContext ctx) {
		if (ctx.getAttribute("SportsFacilityService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<SportsFacility> sportsFacilityRepository = new SportsFacilityRepository(contextPath);
	    	
	    	IDAO<SportsFacility> sportsFacilityDAO = new SportsFacilityDAO(sportsFacilityRepository);
	    	
			ctx.setAttribute("SportsFacilityService", new SportsFacilityService(sportsFacilityDAO));
		}
	}
	public static void initSportsFacilityTypeService(ServletContext ctx) {
		if (ctx.getAttribute("SportsFacilityTypeService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<SportsFacilityType> sportsFacilityTypeRepository = new SportsFacilityTypeRepository(contextPath);
	    	
	    	IDAO<SportsFacilityType> sportsFacilityTypeDAO = new SportsFacilityTypeDAO(sportsFacilityTypeRepository);
	    	
			ctx.setAttribute("SportsFacilityTypeService", new SportsFacilityTypeService(sportsFacilityTypeDAO));
		}
	}
	public static void initFacilityContentService(ServletContext ctx) {
		if (ctx.getAttribute("FacilityContentService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<FacilityContent> facilityContentRepository = new FacilityContentRepository(contextPath);
	    	
	    	IDAO<FacilityContent> sportsFacilityDAO = new FacilityContentDAO(facilityContentRepository);
	    	
			ctx.setAttribute("FacilityContentService", new FacilityContentService(sportsFacilityDAO));
		}
  }
	public static void initBuyerTypeService(ServletContext ctx) {
		if (ctx.getAttribute("BuyerTypeService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<BuyerType> buyerTypeRepository = new BuyerTypeRepository(contextPath);
	    	
	    	IDAO<BuyerType> buyerTypeDAO = new BuyerTypeDAO(buyerTypeRepository);
	    	
			ctx.setAttribute("BuyerTypeService", new BuyerTypeService(buyerTypeDAO));
		}
  }
	public static void initTrainingService(ServletContext ctx) {
		if (ctx.getAttribute("TrainingService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<Training> trainingRepository = new TrainingRepository(contextPath);
	    	
	    	IDAO<Training> trainingDAO = new TrainingDAO(trainingRepository);
	    	
			ctx.setAttribute("TrainingService", new TrainingService(trainingDAO));
		}
  }
	public static void initTrainingTypeService(ServletContext ctx) {
		if (ctx.getAttribute("TrainingTypeService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<TrainingType> trainingTypeRepository = new TrainingTypeRepository(contextPath);
	    	
	    	IDAO<TrainingType> trainingTypeDAO = new TrainingTypeDAO(trainingTypeRepository);
	    	
			ctx.setAttribute("TrainingTypeService", new TrainingTypeService(trainingTypeDAO));
		}
	}
	
	public static void initMembershipService(ServletContext ctx) {
		if (ctx.getAttribute("MembershipService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<Membership> membershipRepository = new MembershipRepository(contextPath);
	    	
	    	IDAO<Membership> membershipDAO = new MembershipDAO(membershipRepository);
	    	
			ctx.setAttribute("MembershipService", new MembershipService(membershipDAO));
		}
	}
	public static void initMembershipTypeService(ServletContext ctx) {
		if (ctx.getAttribute("MembershipTypeService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<MembershipType> membershipTypeRepository = new MembershipTypeRepository(contextPath);
	    	
	    	IDAO<MembershipType> membershipTypeDAO = new MembershipTypeDAO(membershipTypeRepository);
	    	
			ctx.setAttribute("MembershipTypeService", new MembershipTypeService(membershipTypeDAO));
		}
	}
	public static void initPromoCodeService(ServletContext ctx) {
		if (ctx.getAttribute("PromoCodeService") == null) {
	    	String contextPath = ctx.getRealPath("");
	    	IRepository<PromoCode> promoCodeRepository = new PromoCodeRepository(contextPath);
	    	
	    	IDAO<PromoCode> promoCodeDAO = new PromoCodeDAO(promoCodeRepository);
	    	
			ctx.setAttribute("PromoCodeService", new PromoCodeService(promoCodeDAO));
		}
	}

}
