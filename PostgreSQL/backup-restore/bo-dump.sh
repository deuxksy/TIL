PGPASSWORD="p@ssw0rd" pg_dump --host=db.host.com --port=5432 --username=username --dbname=database --format=t --verbose -f ~/backup/db-$1.tar.gz
