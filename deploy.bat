@call cd ..

@rem Downloading dependencies
@if not exist utils (
	@echo Cloning git repo for project utils
    @call git clone --branch 2.0-SNAPSHOT --single-branch https://github.com/Pierre-Emmanuel41/utils.git
) else ( 
	@call cd utils
	
	@echo Pulling latest changes for project utils
	@call git pull
	
	@call cd ..
)

@if not exist protocol (
	@echo Cloning git repo for project protocol
    @call git clone --branch 1.0-SNAPSHOT --single-branch https://github.com/Pierre-Emmanuel41/protocol.git
) else ( 
	@call cd protocol
	
	@echo Pulling latest changes for project protocol
	@call git pull
	
	@call cd ..
)

@rem Building dependencies
@echo Building project utils
@call cd utils
@call mvn clean package install
@call cd ..

@echo Building project protocol
@call cd protocol
@call mvn clean package install
@call cd ..

@echo Building project voxy-common
@call cd voxy-common
@call mvn clean package install