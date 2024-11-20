import java.sql.*;
import java.util.Scanner;

public class createown {
    
    public static void main(String[] args) {
        // Setting URL, password, and username
        final String URL = "jdbc:mysql://localhost:3306/myfirstdb";
        final String USERNAME = "root";
        final String PASSWORD = "9835397556";
        try {
            // 1> Loading and registering driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2> Creating connection
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database connection established!!!!!");
            
            // 3> Collecting user input
            Scanner sc = new Scanner(System.in);
            int id=0;
            String name;
            String roll;
            int[] bookid=new int[5];
            int option=0;
            int nbook_issued=0;
            int newnbook=0;
            int i=0;
            while (option!=4) {
                System.out.println("choose 1 For taking in the input for new users");
                System.out.println("choose 2 For taking in the input for existing users");
                System.out.println("choose 3 For extracting student details");
                System.out.println("choose 4 to get Exit");
                option=sc.nextInt();
                switch (option) {
                    case 1:
                        i=0;
                        System.out.println("Enter Id");
                        id=sc.nextInt();
                        System.out.println("Enter your name");
                        sc.nextLine();
                        name=sc.nextLine();
                        System.out.println("Enter your roll number");
                        roll=sc.nextLine();
                        System.out.println("Enter the number of book to be Issued");
                        newnbook=sc.nextInt();
                        while (newnbook<5 && i<newnbook) {
                            System.out.println("Enter the id of book "+(i+1));
                            bookid[i]=sc.nextInt();
                            nbook_issued++;
                            i++;
                        }
                        String query = "INSERT INTO student (id, name, roll, id_book1, id_book2,id_book3,id_book4,id_book5,nbook_issued) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
                        PreparedStatement ps = con.prepareStatement(query);
                
                        // 5> Setting values to the PreparedStatement
                        ps.setInt(1, id);
                        ps.setString(2, name);
                        ps.setString(3, roll);
                        ps.setInt(4, bookid[0]);
                        ps.setInt(5, bookid[1]);
                        ps.setInt(6, bookid[2]);
                        ps.setInt(7, bookid[3]);
                        ps.setInt(8, bookid[4]);
                        ps.setInt(9, nbook_issued);
                
                        // 6> Executing SQL statement for insertion
                        ps.executeUpdate();
                        System.out.println("Data inserted successfully!");
                        ps.close();
                        break;
                    case 2:
                    i=0;
                    System.out.println("Enter the roll no. you want to search");
                    sc.nextLine();
                    roll=sc.nextLine();
                    String query2 = "SELECT * from student where roll=?";
                    PreparedStatement ps1=con.prepareStatement(query2);
                    ps1.setString(1,roll);
                    ResultSet rs=ps1.executeQuery();
                    if (rs.next()) {
                        id=rs.getInt("id");
                        name=rs.getString("name");
                        roll=rs.getString("roll");
                        bookid[0]=rs.getInt("id_book1");
                        bookid[1]=rs.getInt("id_book2");
                        bookid[2]=rs.getInt("id_book3");
                        bookid[3]=rs.getInt("id_book4");
                        bookid[4]=rs.getInt("id_book5");
                        nbook_issued=rs.getInt("nbook_issued");
                        System.out.println("id     name     roll    id_book1    id_book2    id_book3      id_book4     id_book5");
                        System.out.println(id+"    "+name+"    "+roll+"    "+bookid[0]+"    "+bookid[1]+"    "+bookid[2]+"    "+bookid[3]+"    "+bookid[4]);
                    }
                    else{
                        System.out.println("Student with this roll number not found!!!");
                    }
                        System.out.println("Enter the number of book to be issued");
                        newnbook=sc.nextInt();
                        if ((newnbook+nbook_issued)<=5) {
                        while (i<newnbook) {
                            System.out.println("Enter the id of the book"+(++nbook_issued));
                            bookid[i]=sc.nextInt();
                            String query3="UPDATE student SET  id_book"+(nbook_issued)+"=?,nbook_issued=? where roll=?";
                            ps1=con.prepareStatement(query3);
                            ps1.setInt(1, bookid[i]);
                            ps1.setInt(2,(nbook_issued));
                            ps1.setString(3,roll);
                            ps1.executeUpdate();
                            System.out.println("Book"+(nbook_issued)+"Added");
                            i++;
                        }
                        }
                        else
                            System.out.println("Number of book will exceed 5");
                        ps1.close();
                        break;

                    case 3:
                    System.out.println("Enter the id:");
                    id = sc.nextInt();
                
                    sc.nextLine(); // Consume the newline character after nextInt()
                
                    System.out.println("Enter your name:");
                    name = sc.nextLine();
                
                    System.out.println("Enter the roll number:");
                    roll = sc.nextLine();
                    
                    PreparedStatement ps2=con.prepareStatement("SELECT nbook_issued from student where roll=?");
                    ps2.setInt(1, nbook_issued);
                    switch (nbook_issued) {
                        case 0:
                            System.out.println("Enter the id_book1:");
                            bookid[0] = sc.nextInt();
                            nbook_issued++;
                            break;
                        case 1:
                            System.out.println("Enter the id_book2:");
                            bookid[1] = sc.nextInt();
                            nbook_issued++;
                            break;
                        case 2:
                            System.out.println("Enter the id_book3:");
                            bookid[2] = sc.nextInt();
                            nbook_issued++;
                            break;
                        case 3:
                            System.out.println("Enter the id_book4:");
                            bookid[3] = sc.nextInt();
                            nbook_issued++;
                            break;
                        default:
                            break;
                    }
                    default:
                        break;
                }}
            // 7> Closing resources
            
            
            con.close();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
