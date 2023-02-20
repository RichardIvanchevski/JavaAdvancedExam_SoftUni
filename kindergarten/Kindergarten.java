package kindergarten;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Kindergarten {
    private String name;
    private int capacity;
    private List<Child> registry;

    public Kindergarten(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.registry = new ArrayList<>();
    }

    public boolean addChild(Child child) {
        if (registry.size() < capacity) {
            registry.add(child);
            return true;
        }
        return false;
    }

    public boolean removeChild(String firstName) {
//        for (Child child : registry) {
//            if (child.getFirstName().equals(firstName)) {
//                registry.remove(child);
//                return true;
//            }
//        }
//        return false;
        return this.registry.removeIf(p -> p.getFirstName().equals(firstName));
    }

    public int getChildrenCount() {
        return registry.size();
    }

    public Child getChild(String firstName) {
//        for (Child child : registry) {
//            if (child.getFirstName().equals(firstName)) {
//                return child;
//            }
//        }
//        return null;
        return registry.stream()
                .filter(child -> child.getFirstName().equals(firstName))
                .findFirst()
                .orElse(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Child> getRegistry() {
        return registry;
    }

    public void setRegistry(List<Child> registry) {
        this.registry = registry;
    }

    public String registryReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("Registered children in ").append(name).append(":").append(System.lineSeparator());

        Collections.sort(registry, Comparator.comparingInt(Child::getAge)
                .thenComparing(Child::getLastName)
                .thenComparing(Child::getFirstName));


        for (Child child : registry) {
            sb.append("--").append(System.lineSeparator());
            sb.append(child).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
