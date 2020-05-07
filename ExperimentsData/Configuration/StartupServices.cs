using AutoMapper;
using ExperimentsData.Services;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

namespace ExperimentsData.Configuration
{
    public class StartupServices
    {
        public static void CreateServices(IServiceCollection services, IConfiguration configuration)
        {

            services.AddScoped<IDatasetService,DatasetService>();
            
            var mappingConfig = new MapperConfiguration(mc => { mc.AddProfile(new MapperProfile()); });

            IMapper mapper = mappingConfig.CreateMapper();
            services.AddSingleton(mapper);
            
        }
    }

}