package com.assignment.three;

import com.assignment.three.auth.BlogAuthenticator;
import com.assignment.three.auth.BlogAuthorizer;
import com.assignment.three.core.Author;
import com.assignment.three.core.Blog;
import com.assignment.three.core.User;
import com.assignment.three.db.AuthorDAO;
import com.assignment.three.db.BlogDAO;
import com.assignment.three.resource.AdminResource;
import com.assignment.three.resource.AuthorResource;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import com.assignment.three.resource.BlogResource;
import com.assignment.three.configuration.BlogConfiguration;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;



public class BlogApplication extends Application<BlogConfiguration>{

    private static final HibernateBundle<BlogConfiguration>
            hibernateBundle = new HibernateBundle<BlogConfiguration>(Blog.class, Author.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(
                BlogConfiguration configuration
        ) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Exception {
        new BlogApplication().run(args);
    }


    @Override
    public void initialize(Bootstrap<BlogConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }
    @Override
    public void run(BlogConfiguration configuration,
                    Environment environment) {

    final BlogDAO blogDAO=new BlogDAO(hibernateBundle.getSessionFactory());
    final AuthorDAO authorDAO=new AuthorDAO(hibernateBundle.getSessionFactory());

    final BlogAuthenticator blogAuthenticator=new BlogAuthenticator();
    final  BlogAuthorizer blogAuthorizer=new BlogAuthorizer();

    environment.jersey().register(new BlogResource(blogDAO));
    environment.jersey().register(new AuthorResource(authorDAO,blogAuthenticator));
    environment.jersey().register(new AdminResource(authorDAO,blogAuthenticator));

    environment.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                .setAuthenticator(blogAuthenticator)
                .setAuthorizer(blogAuthorizer)
//                .setRealm("SUPER SECRET STUFF")
                .buildAuthFilter()));

    environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    environment.jersey().register(RolesAllowedDynamicFeature.class);
    }
}
