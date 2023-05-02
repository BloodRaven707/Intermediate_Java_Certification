// import java.util.Arrays;
// import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main( String[] args ) {
        Scanner iScanner = new Scanner( System.in, "CP866" );

        Shop shop = new Shop();
        String filePath = "products.csv";
        shop.addFromFile( filePath );

        int    id     = 0;
        String name   = "";
        int    count  = 0;
        int    chance = 0;

        int intInput = 0;
        int command = 0;
        while (true) {
            View.Menu.Show();
            command = getIntInput( "Введите номер команды", (View.Menu.Size() - 1), iScanner, "Выход" );

            if ( command != 0 ) {
                System.out.println( "\nВы выбрали: " + View.Menu.getMenuItem( command ) );
                System.out.println( View.Line() );
            }
            iScanner.nextLine();

            switch ( command ) {
                case 0:
                    iScanner.close();
                    System.out.println( "\n[+] Выход из приложения" );
                    System.exit( 0 );
                    break;

                case 1: // Список разыгрываеммых товаров
                    View.LotterryListShow( shop.inLotterry() );
                    break;

                case 2: // Сохранить в файл
                    shop.saveFromFile( filePath, shop.getProducts() );
                    break;

                case 3: // Добавьте товар в розыгрыш
                    id = shop.getNextID();
                    System.out.print("Введите название товара: ");
                    name = iScanner.nextLine();

                    intInput = getIntInput( "Введите количество товара", 10000, iScanner, "Отмена" );
                    if ( intInput != 0 ) {
                        count = intInput;
                    } else { continue; }

                    intInput = getIntInput( "Введите шанс получить товара", 100, iScanner, "Отмена" );
                    if ( intInput != 0 ) {
                        chance = intInput;
                    } else { continue; }

                    shop.add( id, name, count, chance );
                    View.LotterryListShow( shop.inLotterry() );
                    System.out.println( "\n[+] Новый товар успешно добавлен в розыгрыш" );
                    System.out.println( View.Line() );
                    break;

                case 4: // Удалить товар из розыгрыша
                    View.LotterryListShow( shop.inLotterry() );
                    id = getIntInput( "Введите ID товара который хотите удалить", shop.getProductsCount(), iScanner, "Отмена" );
                    shop.del( id );
                    View.LotterryListShow( shop.inLotterry() );
                    System.out.println( "\n[+] Товар ID " + id + " успешно удален из розыгрыша" );
                    System.out.println( View.Line() );
                    break;

                case 5: // Изменить шанс получения товара
                    View.LotterryListShow( shop.inLotterry() );
                    id = getIntInput( "Введите ID товара, чтобы изменить шанс получения", shop.getNextID() - 1, iScanner, "Отмена" );
                    int index = shop.getIndex( id );
                    if ( index != -1 ) {
                        intInput = getIntInput( "Введите шанс получить товара", 100, iScanner, "Отмена" );
                        if ( intInput != 0 ) {
                            chance = intInput;
                        } else {
                            shop.setZeroChance( index );
                        }
                        shop.updChance( index, chance );
                        System.out.println( "[+] Шанс получить товар с ID " + id + " изменен" );
                        System.out.println( View.Line() );
                    } else {
                        System.out.println( "[!] Товар с ID " + id + " отсутствует" );
                        System.out.println( View.Line() );
                    }

                case 6: //"Начать игру":
                    if ( shop.getProductsCount() == 0 || shop.getLotterryCount() == 0 ) {
                        System.out.println("Список игрушек для розыгрыша пуст!");
                        System.out.println( View.Line() );
                        break;
                    }

                    intInput = getIntInput( "Введите количество розыгрышей", Integer.MAX_VALUE, iScanner, "Отмена" );
                    if ( intInput != 0 ) {
                        shop.Lottery( intInput );
                    } else { continue; }
                    break;
            }
        }
    }


    // Проверка числового ввода
    public static int getIntInput( String query, int check, Scanner iScanner, String end ) {
        int intInput = 0;
        String errInput = "";
        boolean validInput = false;
        while ( !validInput ) {
            System.out.println( "0. " + end );
            System.out.print( "\n" + query + ": " );

            if ( iScanner.hasNextInt() ) {
                intInput = iScanner.nextInt();
                validInput = ( intInput >= 0 && intInput <= check );
            } else { errInput = iScanner.next(); }

            if ( !validInput ) {
                System.out.println( "\n" + View.Line() );
                System.out.println( "[!] Вы ввели: " + errInput );
                System.out.println( "[!] Неверный ввод." );
                System.out.println( View.Line() );
            } else if ( intInput == 0 ) {
                System.out.println( "[!] Вы отменили действие." );
                System.out.println( View.Line() );
            }
        }
        return intInput;
    }
}
