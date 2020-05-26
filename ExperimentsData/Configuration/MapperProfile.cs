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
            CreateMap<DatasetEntity, DatasetListDTO>()
                .ForMember(x=>x.Samples,x=>x.MapFrom(y=>y.Samples.Count))
                .ReverseMap();

            
            CreateMap<SampleGroupedEntity, SampleListDTO>().ReverseMap();
            CreateMap<SampleEntity,SampleRegisterDTO>().ReverseMap();
            CreateMap<SampleEntity, SampleListDTO>()
                .ForMember(x=>x.Attributes,x=>x.MapFrom(y=>y.Attributes.Count))
                .ReverseMap();
            
            
            CreateMap<AttributeEntity,AttributeRegisterDto>().ReverseMap();
            


        }
    }

}