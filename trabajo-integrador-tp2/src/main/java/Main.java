import factories.FactoryEntity;

public class Main {
    public static void main(String[] args) {
        FactoryEntity mySqlFactory = FactoryEntity.getFactoryEntity(FactoryEntity.MY_SQL);
    }
}
