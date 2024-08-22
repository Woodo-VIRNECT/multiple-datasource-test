# Github-workflow test
1. act 설치 : brew install act
2. act 실행 : act -l
3. .github/workflows/ 폴더에 있는 yml 파일의 pr.yml 실행 : act -P ubuntu-22.04=catthehacker/ubuntu:act-22.04 pull_request
4. JAVA_HOME 설정: export JAVA_HOME=`/usr/libexec/java_home -v 17`
5. ./gradlew clean test