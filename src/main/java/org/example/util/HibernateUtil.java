package org.example.util;


import org.example.model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Crear registro
                registry = new StandardServiceRegistryBuilder()
                        .configure()
                        .build();

                // Crear MetadataSources
                MetadataSources sources = new MetadataSources(registry);
                sources.addAnnotatedClass(Car.class);
                sources.addAnnotatedClass(Supplier.class);
                sources.addAnnotatedClass(ExtraDetail.class);
                sources.addAnnotatedClass(OpeningHour.class);
                sources.addAnnotatedClass(PickupDetail.class);
                sources.addAnnotatedClass(DropoffDetail.class);
                sources.addAnnotatedClass(SupplierExtra.class);
                sources.addAnnotatedClass(TermsCondition.class);



                // Crear Metadata
                Metadata metadata = sources.getMetadataBuilder().build();

                // Crear SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}

