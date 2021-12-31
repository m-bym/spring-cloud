dependencies {
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-bootstrap")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    //jwt parser
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("javax.xml.bind:jaxb-api")
}
