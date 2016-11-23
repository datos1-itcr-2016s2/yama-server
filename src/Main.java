
public class Main{
public static void main(String[] args) {
        BTree<String> st = new BTree<String>();

        String[] a = {"Yose",  "Daniel", "Tavo"};
        String[] b = {"Tavo",  "Daniel", "Yose"};
        String[] c = {"Daniel",  "Daniel", "Yose"};
        
       /* st.put("Hola!", a, 5);
        st.put("Como estas?", b, 5);
        st.put("Bien y vos?", a, 5);
        st.put("Perdiendo el semestre!", b, 5);*/
        Entry m1 = new Entry("Hola!", a, 5, null);
        Entry m2 = new Entry("Como estas?", b, 6, null);
        Entry m3 = new Entry("Bien y vos?", a, 7, null);
        Entry m4 = new Entry("Perdiendo el semestre!", b, 8, null);
        
        st.putMessage(m1);
        st.putMessage(m2);
        st.putMessage(m3);
        st.putMessage(m4);
        
        System.out.println();

        System.out.println("size:    " + st.size());
        System.out.println("height:  " + st.height());
        System.out.println(st);
       
        System.out.println();
        System.out.println(st.getMessageData("Bien y vos?"));
    }
}
