package lab.proj.model;

public interface RoomEffectVisitor {
    void VisitGasPoisoning(RoomEffect effect);
    void VisitCurse(RoomEffect effect);
}
