PGPASSWORD="p@ssw0rd" pg_restore --host=db.host.com --port=5432 --username=username --dbname=dbname --format=t --verbose --create --clean ~/backup/db-$1.tar.gz
