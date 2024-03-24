package lab.proj.model;

public interface ActorVisitor {
    void VisitStudent(Student s);

    void VisitTeacher(Teacher t);
}
