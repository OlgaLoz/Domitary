package Utils;

import Model.Role;

import java.util.HashMap;
import java.util.Map;

public class RoleControl {
    private Map<Role, String> pages;

    public RoleControl() {
        pages = new HashMap<Role, String>();
        pages.put(Role.Student, Pages.HOME_STUDENT.getPagePath());
        pages.put(Role.Doctor, Pages.HOME_DOCTOR.getPagePath());
        pages.put(Role.Governor, Pages.HOME_GOVERNOR.getPagePath());
        pages.put(Role.DeaneryWorker,Pages.HOME_DEANERY.getPagePath());
    }

    public String getPagePathByRole(Role role) {
        return pages.get(role);
    }
}
