using System;
using System.Collections.Generic;
using AutoMapper;
using ExperimentsData.Models.DAO;
using ExperimentsData.Models.DTO;
using ExperimentsData.Repositories;

namespace ExperimentsData.Services.Impl
{
    public class AttributeService : IAttributeService
    {
        private IAttributeRepository _repository;
        private IDatasetRepository _datasetRepository;
        private ISampleRepository _sampleRepository;
        private IMapper _mapper;

        public AttributeService(ISampleRepository sampleRepository, IDatasetRepository datasetRepository, IAttributeRepository repository, IMapper mapper)
        {
            _sampleRepository = sampleRepository;
            _datasetRepository = datasetRepository;
            _repository = repository;
            _mapper = mapper;
        }

        public List<AttributeRegisterDto> getAll(Guid sampleId)
        {
            List<AttributeEntity> result = _repository.GetAllBySampleId(sampleId);
            return _mapper.Map<List<AttributeRegisterDto>>(result);
        }

        public AttributeRegisterDto create(Guid sampleId, AttributeRegisterDto registerDto)
        {
            var entity = _mapper.Map<AttributeEntity>(registerDto);
            entity.Id = new Guid();
            entity.SampleEntity = _sampleRepository.GetById(sampleId);
            if(entity.SampleEntity == null) throw new ArgumentException("THIS SAMPLE DOES NOT EXISTS.");
            entity.SampleEntityId = entity.SampleEntity.Id;
            _repository.Save(entity);
            return registerDto;
        }
    }
}