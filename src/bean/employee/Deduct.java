package bean.employee;

public class Deduct {
    private long eid;//员工外键
    private float deduct1;//子女教育扣除额
    private float deduct2;//赡养老人
    private float deduct3;//继续教育
    private float deduct4;//大病医疗
    private float deduct5;//住房贷款利息
    private float deduct6;//住房租金

    public Deduct() {
    }

    public Deduct(long eid, float deduct1, float deduct2, float deduct3, float deduct4, float deduct5, float deduct6) {
        this.eid = eid;
        this.deduct1 = deduct1;
        this.deduct2 = deduct2;
        this.deduct3 = deduct3;
        this.deduct4 = deduct4;
        this.deduct5 = deduct5;
        this.deduct6 = deduct6;
    }

    public long getEid() {
        return eid;
    }

    public void setEid(long eid) {
        this.eid = eid;
    }

    public float getDeduct1() {
        return deduct1;
    }

    public void setDeduct1(float deduct1) {
        this.deduct1 = deduct1;
    }

    public float getDeduct2() {
        return deduct2;
    }

    public void setDeduct2(float deduct2) {
        this.deduct2 = deduct2;
    }

    public float getDeduct3() {
        return deduct3;
    }

    public void setDeduct3(float deduct3) {
        this.deduct3 = deduct3;
    }

    public float getDeduct4() {
        return deduct4;
    }

    public void setDeduct4(float deduct4) {
        this.deduct4 = deduct4;
    }

    public float getDeduct5() {
        return deduct5;
    }

    public void setDeduct5(float deduct5) {
        this.deduct5 = deduct5;
    }

    public float getDeduct6() {
        return deduct6;
    }

    public void setDeduct6(float deduct6) {
        this.deduct6 = deduct6;
    }

    @Override
    public String toString() {
        return "Deduct{" +
                "eid=" + eid +
                ", deduct1=" + deduct1 +
                ", deduct2=" + deduct2 +
                ", deduct3=" + deduct3 +
                ", deduct4=" + deduct4 +
                ", deduct5=" + deduct5 +
                ", deduct6=" + deduct6 +
                '}';
    }
}