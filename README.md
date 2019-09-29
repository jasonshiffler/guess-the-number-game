This is a basic spring boot application based on a project from Tim Buchalka's Spring Master class. The application
is a number guessing game that generates a random number for the player to guess within a specified number of guesses.
The game parameters can be configured within the game.properties file. Spring annotations that demonstrated include the
following:

@Springboot
@Configuration
@Component
@Bean
@Qualifier
@EventListener
@PreDestroy
@PostConstruct

Constructor injection is used to inject the beans along with the @Qualifier annontation to explicity state the
name of the bean to be injected. There are a few Lombok annotations as well to reduce the amount of boilerplate code
required for getters and setters as well as logging.