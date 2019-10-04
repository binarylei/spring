package binarylei.jaxrs.feign;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.jaxrs.JAXRSContract;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.stream.Collectors;

public class GitHubExample {

    public static void main(String... args) {
        GitHub github = Feign.builder()
                .contract(new JAXRSContract())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.BASIC)
                .target(GitHub.class, "https://api.github.com");

        System.out.println("Let's fetch and print a list of the contributors to this org.");
        List<String> contributors = github.contributors("openfeign");
        for (String contributor : contributors) {
            System.out.println(contributor);
        }
    }

    interface GitHub {

        class Repository {
            String name;
        }

        class Contributor {
            String login;
        }

        @GET
        @Path("/users/{username}/repos?sort=full_name")
        List<Repository> repos(@PathParam("username") String owner);

        @GET
        @Path("/repos/{owner}/{repo}/contributors")
        List<Contributor> contributors(@PathParam("owner") String owner,
                @PathParam("repo") String repo);

        default List<String> contributors(String owner) {
            return repos(owner).stream()
                    .flatMap(repo -> contributors(owner, repo.name).stream())
                    .map(c -> c.login)
                    .distinct()
                    .collect(Collectors.toList());
        }
    }

}
