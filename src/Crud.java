import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
public class Crud {

	public static void main(String[] args) {
		Configuration cfg = new Configuration().configure();
		SessionFactory factory = cfg.buildSessionFactory();
		Session session=factory.openSession();
		Transaction txn=session.beginTransaction();
		do {
		System.out.println("Enter Your Choice");
		System.out.println(" 1. Insert \n 2. Update \n 3. Search \n 4. Delete \n 5. exit");
		Scanner s=new Scanner(System.in);
		int n=s.nextInt();
		switch(n) 
		{
		case 1:
			Scanner scanner = new Scanner(System.in);

	        System.out.print("Enter student roll number: ");
	        int rollNumber = scanner.nextInt();

	        System.out.print("Enter student name: ");
	        String studentName = scanner.next();

	        System.out.print("Enter student marks: ");
	        int marks = scanner.nextInt();

	        Student s1 = new Student();
	        s1.setRno(rollNumber);
	        s1.setName(studentName);
	        s1.setMarks(marks);

	        session.save(s1);
	        txn.commit();

	        System.out.println("Data Inserted Successfully....");
	        break;
	        
		case 2:
			Scanner scanner1 = new Scanner(System.in);

	        System.out.print("Enter student ID to update: ");
	        int studentId = scanner1.nextInt();

	        Student student = session.get(Student.class, studentId);

	        if (student != null) {
	            System.out.print("Enter new name for the student: ");
	            String newName = scanner1.next();
	            student.setName(newName);

	            System.out.print("Enter new marks for the student: ");
	            int newMarks = scanner1.nextInt();
	            student.setMarks(newMarks);

	            session.update(student);
	            txn.commit();
	            System.out.println("Student data updated successfully.");
	        } else {
	            System.out.println("Student with ID " + studentId + " not found.");
	        }
	        break;

		case 3:
			Scanner scanner2 = new Scanner(System.in) ;
				System.out.print("Enter student ID to search: ");
				int studentId1 = scanner2.nextInt();

				Student student2 = session.get(Student.class, studentId1);

				if (student2 != null) {
				    System.out.println("Student found:");
				    System.out.println("ID \t Name \t Marks");
				    System.out.println(student2.getRno()+"\t"+ student2.getName()+"\t"+student2.getMarks());
				    
				} else {
				    System.out.println("Student with ID " + studentId1 + " not found.");
				}
				System.out.println("Show All Data Enter Y :");
				String a1=scanner2.next();
				
				if("Y".equalsIgnoreCase(a1) || "y".equalsIgnoreCase(a1)) {
					
					List<Student>l1=session.createQuery("from Student",Student.class).getResultList();	
					for(Student x:l1) {
						System.out.println(x.getRno()+"\t"+x.getName()+"\t"+x.getMarks());
					}
				}
			
			 break;
			 
			 
		case 4:
			Scanner s4=new Scanner(System.in);
			System.out.println("Enter Roll no. you want delete Record:");
			int a= s4.nextInt();
			Student s5= session.get(Student.class, a);
			
			session.delete(s5);
			txn.commit();
			System.out.println("Data Delete Succesfully....");
			 break;
			 
		case 5:
			System.exit(0);
            break;
        default:
            System.out.println("Invalid choice. Please enter a valid option.");
    }
} while (true);

	}

}
