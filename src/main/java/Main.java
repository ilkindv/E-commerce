import management.Management;
import management.impl.IManagement;

public class Main {
    public static void main(String[] args) {
        Management management = new IManagement();
        management.manage();
    }
}
