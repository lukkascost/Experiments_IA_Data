FROM mcr.microsoft.com/dotnet/sdk:3.1 as build 
COPY . .
RUN dotnet restore
WORKDIR /ExperimentsData
RUN dotnet build -c Relaese -o /app


FROM build AS publish
RUN dotnet publish -c Release -o /app


FROM mcr.microsoft.com/dotnet/sdk:3.1 
WORKDIR /app
COPY --from=publish /app .
ENV TZ 'America/Fortaleza'
RUN echo $TZ > /etc/timezone && \
    apt-get update && apt-get install -y tzdata && \
    rm /etc/localtime && \
    ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && \
    dpkg-reconfigure -f noninteractive tzdata && \
    apt-get clean
ENTRYPOINT ["dotnet", "ExperimentsData.dll"]