package dao;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
  
import org.hibernate.Hibernate;  
import org.hibernate.Session;  
import org.hibernate.SessionFactory;  
import org.hibernate.cfg.AnnotationConfiguration;  
import org.hibernate.cfg.Configuration;  
    

public class HibernateUtil  
{  
    private static SessionFactory sessionFactory;  
    private static AnnotationConfiguration configuration=new AnnotationConfiguration();
    private static boolean annotationActive = false;  
    private static final ThreadLocal threadSession = new ThreadLocal();  
      
    public static void initialize()  
    {  
        try  
        {   sessionFactory = configuration.configure().buildSessionFactory();
            sessionFactory.openSession();
            System.out.println( ">> HIBERNATE INICIADO COM SUCESSO." );
        }  
        catch ( Throwable e )  
        {  
           System.out.println( ">> FALHA NA INICIA��O DO HIBERNATE." );  
           e.printStackTrace();  
        }  
    }  
  
    public static Configuration getConfiguration() {  
          if( configuration == null )  
             configuration =  new AnnotationConfiguration();
          return configuration;  
   }  
  
   public static void setConfiguration(AnnotationConfiguration cfg) {
      HibernateUtil.configuration = cfg;  
   }  
  
   public static boolean isAnnotationActive() {  
      return annotationActive;  
   }  
  
   public static void setAnnotationActive(boolean annotationActive) {  
      HibernateUtil.annotationActive = annotationActive;  
   }  
     
   public static void reinitialize(){  
      closeSession();  
      initialize();  
      getSession();  
   }  
  
  
   /** 
     * Inicializa as propriedades de um objeto persistido. 
     */  
    public static void initializeProperties( Object obj )  
    {  
        if ( !Hibernate.isInitialized( obj ) )  
        {  
            Hibernate.initialize( obj );  
        }  
    }  
  
  
    /** 
     * Atualiza um objeto persistido. 
     */  
    public static void refreshObject( Object obj )  
    {  
        Session s = HibernateUtil.getSession();  
        s.refresh( obj );  
    }  
  
  
    /** 
     * Limpa a session corrente. 
     */  
    public static void clearSession()  
    {  
        Session s = HibernateUtil.getSession();  
        s.clear();  
    }  
  
  
    /** 
     * Retorna uma Session existente, ou ent�o abre uma nova e associa a ThreadLocal. 
     * @return uma sess�o do Hibernate 
     */  
    public static Session getSession()  
    {  
        if( sessionFactory == null ){
            initialize();
            closeSession();
        }

        Session s = sessionFactory.openSession();
        threadSession.set( s );
        return s;  
    }  
  
  
    /** 
     * Fecha a Session associada a ThreadLocal. 
     */  
    public static void closeSession()  
    {  
        Session s = (Session) threadSession.get();  
        threadSession.set( null );  
        if ( s != null && s.isOpen() )  
        {  
            s.close();  
        }  
    }

    public static void main(String args[]){
        Session session=HibernateUtil.getSession();
        session.getTransaction().begin();
       
        session.close();
    }
}  
