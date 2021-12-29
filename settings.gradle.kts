rootProject.name = "spring-cloud"
include("discovery-app")
include("gateway-app")
include("config-app")
include("service-apps:user-service-app")
include("service-apps:catalog-service-app")
findProject(":service-apps:catalog-service-app")?.name = "catalog-service-app"
include("service-apps:order-service")
findProject(":service-apps:order-service")?.name = "order-service"
