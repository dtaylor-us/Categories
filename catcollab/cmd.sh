#!/bin/bash

export DATABASE_URL=$(heroku config | grep CLEARDB_DATABASE_URL | sed 's/^.*: //')
export APP_NAME=$(heroku apps:info -s  | grep web_url | cut -d= -f2 | awk -F/ '{print $3}')

clean() {
    echo "*****************************************************"
    echo "     Cleaning project..."
    echo "*****************************************************"
    mvn clean
}
 
compile() {
    echo "*****************************************************"
    echo "     Compiling.."
    echo "*****************************************************"
    mvn compile
}
 
install() {
    echo "*****************************************************"
    echo "     Running Install..."
    echo "*****************************************************"
    mvn install
}
 
deploy_war() {
    echo "*****************************************************"
    echo "     Deploying war to $APP_NAME..."
    echo "*****************************************************"
    mvn heroku:deploy-war
}

migrate_schema() {
    echo "*****************************************************"
    echo "     Initializing Migration..."
    echo "*****************************************************"
    mvn initialize flyway:migrate
}

start_server() {
    echo "******************************************************"
    echo "      Starting Tomcat Server..."
    echo "******************************************************"
    mvn tomcat:run-war
}
 
case $1 in
    clean)
        clean
        ;;
    compile)
        compile
        ;;
    install)
        install
        ;;
    deploy)
        deploy_war
        ;;
    migrate)
        migrate_schema
        ;;
    run)
        clean
        compile
        install
        start_server
        ;;
    
    esac
exit 0