public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

//        String[] participant = new String[]{"leo", "kiki", "eden"};
//        String[] completion = new String[]{"kiki", "eden"};
        String[] participant = new String[]{"mislav", "stanko", "mislav", "ana"};
        String[] completion = new String[]{"stanko", "ana", "mislav"};

        System.out.println(solution.solution(participant, completion));


    }
}