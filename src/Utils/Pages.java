package Utils;

public enum Pages {
    HOME_GUEST("/index.jsp"),
    HOME_STUDENT("/indexStudent.jsp"),
    HOME_DOCTOR("/indexDoctor.jsp"),
    HOME_GOVERNOR("/indexGovernor.jsp"),
    HOME_DEANERY("/indexDeaneryWorker.jsp"),
    HOME_SETTLERS("/settlerListPage.jsp"),
    DISTR_CANDIDATES("/addToSettlerListPage.jsp"),
    EDIT_DORMITORY("/editDormitory.jsp");

    private String pagePath;

    Pages(String pagePath) {
        this.pagePath = pagePath;
    }

    public String getPagePath() {
        return this.pagePath;
    }
}
