package Utils;

import Model.Role;

import java.util.HashMap;
import java.util.Map;

public class RoleControl {
    private Map<Role, String> pages;

    public RoleControl() {
        pages = new HashMap<Role, String>();
        pages.put(Role.Student, Pages.HOME_STUDENT.getPageName());
        pages.put(Role.Doctor, Pages.HOME_DOCTOR.getPageName());
        pages.put(Role.Governor, Pages.HOME_GOVERNOR.getPageName());
        pages.put(Role.DeaneryWorker,Pages.HOME_DEANERY.getPageName());
        pages.put(Role.Admin, Pages.HOME_ADMIN.getPageName());
    }

    public String getPageNameByRole(Role role) {
        return pages.get(role);
    }
}
