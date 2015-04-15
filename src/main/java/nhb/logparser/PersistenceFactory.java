package nhb.logparser;


public class PersistenceFactory {

    public static Persistence create() {
        return new PersistenceWithJson();
    }

}
