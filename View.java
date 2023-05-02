import java.util.Arrays;
import java.util.List;


public class View {
    static class Menu {
        static List<String> menu = Arrays.asList( "### Главное меню:",
            "Список товаров в розыгрыше",
            "Сохранить информацию о розыгрыше",
            "Добавьте товар в розыгрыш",
            "Удалить товар из розыгрыша",
            "Изменить шанс получить товар",
            "Начать розыгрыш (если розыгрышей < 1000 то что-то может остаться...)"
        );


        public static void Show() {
            System.out.println("\n" + menu.get( 0 ) );
            for ( int i = 1; i < menu.size(); i++ ) {
                System.out.println( ( i ) + ". " + menu.get( i ) );
            }
        }

        public static String getMenuItem( int id ) {
            return menu.get( id );
        }

        public static int Size() {
            return menu.size();
        }
    }


    public static String Line() {
        return String.format( "%s", "-".repeat( 83 ) );
    }


    public static void LotterryListShow( String LotterryList ) {
        System.out.println( LotterryList );
        System.out.println( Line() );
    }
}
