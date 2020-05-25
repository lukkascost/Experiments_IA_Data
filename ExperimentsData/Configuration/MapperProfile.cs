using AutoMapper;
using ExperimentsData.Models.DAO;
using ExperimentsData.Models.DTO;

namespace ExperimentsData.Configuration
{
    public class MapperProfile : Profile
    {
        public MapperProfile()
        {

            CreateMap<DatasetGroupedEntity, DatasetListDTO>().ReverseMap();
            CreateMap<DatasetEntity, DatasetRegisterDTO>().ReverseMap();
            
            
            CreateMap<SampleGroupedEntity, SampleListDTO>().ReverseMap();
            CreateMap<SampleEntity,SampleRegisterDTO>().ReverseMap();
            

        }
    }

}