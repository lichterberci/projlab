package lab.proj.model;

public interface RoomEffectVisitor {
    void VisitGasPoisoning(GasPoisoning gp);

    void VisitCurse(Curse c);
}
