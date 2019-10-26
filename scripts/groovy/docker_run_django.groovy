node {
    
   git 'https://github.com/geek-kb/tikal_devops_task.git'
   sh '''
   docker build -t django1 .
   server_id=$(docker ps -f "ancestor=django1" -q)
   if [ "$server_id" != '' ]
   then
    docker stop $server_id
    docker rm $server_id
   fi
   docker run -d -p 8081:80 django1
   sleep 10
   response=$(python3 test.py)
   [ $response = "200" ]&&exit 0
   exit 1
   '''
}
