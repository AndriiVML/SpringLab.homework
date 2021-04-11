
###homework1

Spring_1 Intro & Core
Annotation-based configuration and Spring Dependency Injection

1.	Підготувати різні POJO класи з анотацією @Component, які розміщені в 3х різних пекеджах: 
a.	beans1 (біни: BeanA, BeanB, BeanC), 
b.	beans2 (біни: RoseFlower, CatAnimal, NarcissusFlower), 
c.	beans3 (біни: BeanD, BeanE, BeanF). 
2.	Створити 2ва файли конфігурації @Configuration.
3.	Перша @Configuration проводить скан компонентів в пекіджі beans1.
4.	Друга @Configuration сканує усі компоненти з beans2, а з beans3 вибирає лише BeanD та BeanF.
5.	В окремому пекеджі створити біни OtherBeanA, OtherBeanB, OtherBeanC з анотацією @Component.
6.	Створити біни, які будуть вприскувати біни з п.5 за допомогою @Autowired в:
a.	конструктор
b.	сеттери
c.	поля
Для деяких з них використати @Qualifier.
7.	Створити декілька бінів, які реалізовують певний інтерфейс. Створити бін,  що буде містити колекцію для створених бінів, які є впорядковані з використанням @Order.
8.	Для п.7 один з бінів позначити @Primary, і вприснути ці біни в новий бін як об’єкти цього інтерфейсу, один з них як основний, а решта по @Qualifier.
9.	Вивести конфігурацію усіх бінів на екран з використанням ApplicationContext.

###homework2

Spring_2 Core
Java-based Container Configuration

1.	Підготувати 6-ть різних POJO класів для створення бінів, з обовязковими полями name, value та методом toString(). Назви бінів: BeanA, BeanB, BeanC, BeanD, BeanE, BeanF.
2.	Біни BeanB, BeanC, BeanD свої значення отримують з *.properties файлу 
3.	Створити 2ва файли конфігурації @Configuration, та імпортувати одну конфігурацію в іншу.
4.	Забезпечити такий порядок створення бінів: BeanD, BeanB, BeanC
5.	Забезпечити конфігурування біна BeanF з @Lazy ініціалізацією.
6.	При конфігуруванні бінів BeanB, BeanC, BeanD за допомогою @Bean анотації, задати назви користувацьких методів для атрибутів initMethod та destroyMethod, в яких виводити відповідні повідомлення.
7.	Бін BeanA повинен реалізовувати інтерфейси InitializingBean та DisposableBean. Перебачити відповідні повідомлення з реалізованих методів.
8.	Бін BeanE повинен мати методи з анотаціями @PostConstruct та @PreDestroy. Перебачити відповідні повідомлення з цих методів.
9.	Вивести перелік усіх бінів, що створені у ApplicationContext. Проаналізувати результати.
10.	Створити окремий бін, що реалізовує BeanFactoryPostProcessor. За допомогою нього для біна BeanB змінити параметр initMethod на значення іншого користувацького методу. Передбачити вивід відповідних повідомлень.
11.	Створити окремий бін, що реалізовує BeanPostProcessor. За допомогою нього здійснити валідацію наших бінів. Усі поля name повинні містити значення (not null), а поля value містити лише додатні значення.
12.	Вивести конфігурацію усіх бінів на екран з використанням ApplicationContext.

###homework4

Spring_4 Web MVC Homework

Required:
1.	Migrate core business logic related functionality from Servlet-based application to Spring MVC adapting the old project to the correct package structure, naming conventions, etc.:
	get rid of all JSP views (now your service will be consuming and populating only JSON text format)
	make your service RESTful (don't implement HATEOAS logic for now. It will be accomplished in the scope of future lectures)
	the best approach is to start from the scratch and create an empty Spring Boot project using the 'Spring Initializr'.
	avoid code copypaste from the old project, unless it relates to the business logic.
	the Spring Boot Actuator must be present in the new project.
	you could use ‘Postman’ or any other tool to make HTTP calls to your server.
2.	Add a logging interceptor to the new 'interceptor' package that will handle all requests and write a log with the user's session id.
3.	Create DTO classes and use them at the controller level.
4.	Add basic validation to DTO classes:
	use standard set of hibernate validator annotations.
	use different validation groups in combination with @Validated annotation.
5.	Add logging to all layers of the application.
6.	Configure the ‘/info’ endpoint of Actuator.

Optional (would be a great plus):
1. Use any of available open source mapping tool in order to remap from business classes to DTO and vice versa (BeanUtils, MapStruct, etc.)
2. Create custom validation annotations using ConstraintValidatoror. Samples:
	check whether there is a user in the database with such username during registration. If so, throw an error.
	make a class level validation annotation that will check whether 2 passwords (‘password’ and ‘repeatPassword’ fields) are the same during registration. If not, throw an error.
3. Add multilingual support for exception messages configuring the ‘MessageSource’ bean - https://www.baeldung.com/spring-custom-validation-message-source

