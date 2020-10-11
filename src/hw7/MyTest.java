package hw7;

public class MyTest {
    @BeforeSuite
    public static void before() {
        System.out.println("Before");
    }

    @Test(priority = 1)
    public static void test1() {
        System.out.println("Test1: priority 1");
    }

    @Test(priority = 2)
    public static void test2() {
        System.out.println("Test2: priority 2");
    }

    @Test(priority = 3)
    public static void test3() {
        System.out.println("Test3: priority 3");
    }

    @Test(priority = 3)
    public static void test4() {
        System.out.println("Test4: priority 3");
    }

    @Test(priority = 1)
    public static void test5() {
        System.out.println("Test5: priority 1");
    }

    @AfterSuite
    public static void after() {
        System.out.println("After");
    }
}
