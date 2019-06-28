#!/bin/sh

mysql -u root --password=$MYSQL_ROOT_PASSWORD < /var/tmp/employees.sql
mysql -u root --password=$MYSQL_ROOT_PASSWORD < /var/tmp/load_departments.dump;
mysql -u root --password=$MYSQL_ROOT_PASSWORD < /var/tmp/load_employees.dump;
mysql -u root --password=$MYSQL_ROOT_PASSWORD < /var/tmp/load_dept_emp.dump;
mysql -u root --password=$MYSQL_ROOT_PASSWORD < /var/tmp/load_dept_manager.dump;
mysql -u root --password=$MYSQL_ROOT_PASSWORD < /var/tmp/load_titles.dump;
mysql -u root --password=$MYSQL_ROOT_PASSWORD < /var/tmp/load_salaries1.dump;
mysql -u root --password=$MYSQL_ROOT_PASSWORD < /var/tmp/load_salaries2.dump;
mysql -u root --password=$MYSQL_ROOT_PASSWORD < /var/tmp/load_salaries3.dump;
