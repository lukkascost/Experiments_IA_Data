using System;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

namespace ExperimentsData.Configuration
{
    public class CodeConstants
    {
        public static String ConnectionString;

        public static void SetConstants(IServiceCollection services, IConfiguration configuration)
        {
            ConnectionString = configuration.GetConnectionString("defaultConnectionString");
        }
    }

}