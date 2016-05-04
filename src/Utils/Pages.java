package Utils;

public enum Pages {
    HOME_GUEST("home_guest"),
    HOME_STUDENT("home_student"),
    HOME_DOCTOR("home_doctor"),
    HOME_GOVERNOR("home_governor"),
    HOME_DEANERY("home_deanery"),
    HOME_ADMIN("home_admin"),
    DISTR_CANDIDATES("distr_candidates"),
    EDIT_DORMITORY("edit_dormitory"),
    EDIT_BLOCK("edit_block"),
    DOCUMENTS_PAGE("documents_page"),
    DOWNLOAD_FILE("download");

    private String pageName;

    Pages(String pagePath) {
        this.pageName = pagePath;
    }

    public String getPageName() {
        return this.pageName;
    }
}
