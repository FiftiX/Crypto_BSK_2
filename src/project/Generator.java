package project;

public class Generator {

    private int sum(int[] seed, int[] polynominal){
        int val = 0;
        for (int i=0;i<polynominal.length;i++){
            if(polynominal[i]==1&&seed[i]==1){
                val++;
            }
        }
        return val % 2;
    }

    public int[] run(int length, int seed[], int polynominal[]){
        int result[] = new int[length];
        int first = 0;
        for(int i=0;i<length;i++){
            first = sum(seed, polynominal);
            result[i]=first;
            for(int j=seed.length-1;j>0;j--){
                seed[j]=seed[j-1];
            }
            seed[0]=first;
        }
        return result;
    }

    public int[] run(int text[], int seed[], int polynominal[], boolean crypt){
        int result[] = new int[text.length];
        int first = 0;
        for(int i=0;i<text.length;i++){
            first = sum(seed, polynominal);
            result[i]=((first+text[i])%2);

            for(int j=seed.length-1;j>0;j--){
                seed[j]=seed[j-1];
            }
            if(crypt==true)
                seed[0]=result[i];
            else
                seed[0]=text[i];
        }
        return result;
    }
}