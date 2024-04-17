package lab.proj.utils;

public class PascalCaseObjectRegistry extends ObjectRegistry {

    @Override
    protected String GenerateNameToObject(Object object) {
        String originalName = object.getClass().getSimpleName();

        StringBuilder newName = new StringBuilder();
        if (originalName.equals("Towel"))
            newName.append("tw");
        else if (originalName.equals("Transistor"))
            newName.append("tr");
        else if (originalName.equals("Camembert"))
            newName.append("cm");
        else
            for (int i = 0; i < originalName.length(); i++)
                if (Character.isUpperCase(originalName.charAt(i)))
                    newName.append(Character.toLowerCase(originalName.charAt(i)));

        String nameOfCreatedObject = newName.toString() + 1;
        for (int id = 2; nameToObject.containsKey(nameOfCreatedObject); id++)
            nameOfCreatedObject = newName.toString() + id;
        return nameOfCreatedObject;
    }
}
