




echo $PATH
DB_PATH=/tmp/applifire/db/6G9DHHYDO12QJFDCJMHK0G/F9861148-17E9-4BA8-AC2B-1A0F89DD407C
MYSQL=/usr/bin
USER=algo
PASSWORD=algo
HOST=localhost


echo 'drop db starts....'
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends....'