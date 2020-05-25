using AutoMapper;
using ExperimentsData.Repositories;
using ExperimentsData.Repositories.Impl;
using ExperimentsData.Services;
using ExperimentsData.Services.Impl;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

namespace ExperimentsData.Configuration
{
    public class StartupServices
    {
        public static void CreateServices(IServiceCollection services, IConfiguration configuration)
        {

            services.AddScoped<IDatasetService,DatasetService>();
            services.AddScoped<IDatasetRepository, DatasetRepository>();

            services.AddScoped<ISampleService,SampleService>();
            services.AddScoped<ISampleRepository, SampleRepository>();
            
            services.AddScoped<IAttributeService,AttributeService>();
            services.AddScoped<IAttributeRepository, AttributeRepository>();

            var mappingConfig = new MapperConfiguration(mc => { mc.AddProfile(new MapperProfile()); });

            IMapper mapper = mappingConfig.CreateMapper();
            services.AddSingleton(mapper);
            
        }
    }

}