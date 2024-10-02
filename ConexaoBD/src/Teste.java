public class Teste {

    public static void main(String[] args) {
        RegistroDB db = new RegistroDB();

        //db.inserir(new Registro(2, "Maria Antonia", "A", 20));
       // db.inserir(new Registro(1, "Pablo Ricardo", "Leal", 20));

       // db.inserir(new Registro(1, "Pablo Ricardo", "Leal", 20));

        //db.inserir(new Registro(5, "Daniel", "A", 18));
        db.selectPorId(1);
        db.selectPorId(2);

          //db.remover(1);

        //List<Registro> registros = db.getRegistros();

       // for(Registro registro : registros) {
//System.out.println("ID > "+registro.getId()+ " Nome> "+registro.getNome());
       // }
    }
}
