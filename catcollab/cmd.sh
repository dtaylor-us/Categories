#!/bin/bash

export DATABASE_URL=$(heroku config | grep CLEARDB_DATABASE_URL | sed 's/^.*: //')
export APP_NAME=$(heroku apps:info -s  | grep web_url | cut -d= -f2 | awk -F/ '{print $3}')

clean() {
    echo "Cleaning project..."
    mvn clean
}
 
compile() {
    echo "Compiling.."
    mvn compile
}
 
install() {
    echo "Running Install..."
    mvn install
}
 
deploy_war() {
    echo "Deploying war to $APP_NAME..."
    mvn heroku:deploy-war
}

migrate_schema() {
    echo "Initializing Migration..."
    mvn initialize flyway:migrate
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
    
    esac
exit 0