package Utils;

public enum Pages {
    HOME_GUEST("/index.jsp"),
    HOME_STUDENT("/pages/student/indexStudent.jsp"),
    HOME_DOCTOR("/pages/doctor/indexDoctor.jsp"),
    HOME_GOVERNOR("/pages/governor/indexGovernor.jsp"),
    HOME_DEANERY("/pages/deanery/indexDeaneryWorker.jsp"),
    HOME_ADMIN("/pages/admin/indexAdmin.jsp"),
    HOME_SETTLERS("/settlerListPage.jsp"),
    DISTR_CANDIDATES("/pages/deanery/addToSettlerListPage.jsp"),
    EDIT_DORMITORY("/pages/admin/editDormitory.jsp"),
    EDIT_BLOCK("/pages/admin/editBlock.jsp");

    private String pagePath;

    Pages(String pagePath) {
        this.pagePath = pagePath;
    }

    public String getPagePath() {
        return this.pagePath;
    }
}
