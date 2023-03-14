package vidmot;

public enum Stefna {
    HAEGRI (360), NIDUR(270), VINSTRI(180), UPP(90);
    private final int gradur;


    Stefna(int s){
        gradur = s;
    }

    /**
     * Getter fyrir gráður
     * @return graður
     */
    public int getGradur(){
        return this.gradur;
    }
}
