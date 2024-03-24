package lab.proj.model;

import lab.proj.utils.IndentedDebugPrinter;

import java.util.Collections;
import java.util.Optional;

public class GasVisitor implements ActorVisitor {
    @Override
    public void VisitStudent(Student s) {
        IndentedDebugPrinter.getInstance().invokeObjectMethod(this, s, "Shock", Collections.emptyList());
        s.Shock();
        IndentedDebugPrinter.getInstance().returnFromMethod(this, s, "Shock", Optional.empty());
    }

    @Override
    public void VisitTeacher(Teacher t) {

    }
}
