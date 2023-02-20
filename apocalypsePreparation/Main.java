package apocalypsePreparation_Exam;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> craftedItems = new LinkedHashMap<>();
        ArrayDeque<Integer> textilesQueue = new ArrayDeque<>();
        ArrayDeque<Integer> medicamentsStack = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split(" ")).forEach(e -> textilesQueue.offer(Integer.parseInt(e)));
        Arrays.stream(scanner.nextLine().split(" ")).forEach(e -> medicamentsStack.push(Integer.parseInt(e)));


        while (!textilesQueue.isEmpty() && !medicamentsStack.isEmpty()) {

            int sumPortion = textilesQueue.peek() + medicamentsStack.peek();

            if (sumPortion == 30) {
                textilesQueue.poll();
                medicamentsStack.pop();
//                craftedItems.putIfAbsent("Patch",0);
//                craftedItems.put("Patch",craftedItems.get("Patch") + 1);
                craftedItems.merge("Patch", 1, Integer::sum);

            } else if (sumPortion == 40) {
                textilesQueue.poll();
                medicamentsStack.pop();
                craftedItems.merge("Bandage", 1, Integer::sum);
            } else if (sumPortion == 100) {
                textilesQueue.poll();
                medicamentsStack.pop();
                craftedItems.merge("MedKit", 1, Integer::sum);
            } else if (sumPortion > 100) {
                int returnMeds = sumPortion - 100;
                textilesQueue.poll();
                medicamentsStack.pop();
                craftedItems.merge("MedKit", 1, Integer::sum);
                returnMeds = returnMeds + medicamentsStack.pop();
                medicamentsStack.push(returnMeds);
            } else {
                textilesQueue.poll();
                int returnMeds = medicamentsStack.pop() + 10;
                medicamentsStack.push(returnMeds);
            }
        }

        printCombinationItems(textilesQueue, medicamentsStack);

        printCraftedItems(craftedItems);
        
        printItemsLeft(textilesQueue, medicamentsStack);

    }

    private static void printItemsLeft(Collection<Integer> textilesQueue, Collection<Integer> medicamentsStack) {
        if (!medicamentsStack.isEmpty()) {
            System.out.print("Medicaments left: ");
            System.out.println(medicamentsStack.stream().collect(Collectors.toList()).stream().map(String::valueOf).collect(Collectors.joining(", ")));
        } else if (!textilesQueue.isEmpty()) {
            System.out.print("Textiles left: ");
            System.out.println(textilesQueue.stream().collect(Collectors.toList()).stream().map(String::valueOf).collect(Collectors.joining(", ")));
        }
    }

    private static void printCraftedItems(Map<String, Integer> craftedItems) {
        if (!craftedItems.isEmpty()) {
            Map<String, Integer> sortedMap = new TreeMap<>(
                    (k1, k2) -> {
                        int cmp = craftedItems.get(k2).compareTo(craftedItems.get(k1));
                        if (cmp != 0) {
                            return cmp;
                        } else {
                            return k1.compareTo(k2);
                        }
                    }
            );
            sortedMap.putAll(craftedItems);

            for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
                System.out.printf("%s - %d%n", entry.getKey(), entry.getValue());
            }
        }
    }

    private static void printCombinationItems(ArrayDeque<Integer> textilesQueue, ArrayDeque<Integer> medicamentsStack) {
        if (textilesQueue.isEmpty() && medicamentsStack.isEmpty()) {
            System.out.println("Textiles and medicaments are both empty.");
        } else if (textilesQueue.isEmpty()) {
            System.out.println("Textiles are empty.");
        } else {
            System.out.println("Medicaments are empty.");
        }
    }
}
