public class TowerOfHanoi {
    static void towerOfHanoiImplementation(int no, char from_rod, char to_rod, char auxiliaryRod) {
        if (no == 1) {
            System.out.println( no + " ==> " + from_rod + " ---> " + to_rod);
            return;
        }
        towerOfHanoiImplementation(no - 1, from_rod, auxiliaryRod, to_rod);
        System.out.println( no + " ==> " + from_rod + " ---> " + to_rod);
        towerOfHanoiImplementation(no - 1, auxiliaryRod, to_rod, from_rod);
    }
    public static void main(String[] args) {
        int no = 4; // Number of disks
        towerOfHanoiImplementation(no, '1', '3', '2');
    }
}
