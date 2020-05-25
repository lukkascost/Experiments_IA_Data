using System;
using System.Collections.Generic;
using System.IO;
using System.Text;
using AutoMapper;
using ExperimentsData.Models.DAO;
using ExperimentsData.Models.DTO;
using ExperimentsData.Repositories;

namespace ExperimentsData.Services.Impl
{
    
    public class DatasetService : IDatasetService
    {
        private IDatasetRepository _repository;
        private IMapper _mapper;

        public DatasetService(IDatasetRepository repository, IMapper mapper)
        {
            _repository = repository;
            _mapper = mapper;
        }

        public List<DatasetListDTO> getAll()
        {
            List<DatasetGroupedEntity> result = _repository.GetAllGrouped();
            return _mapper.Map<List<DatasetListDTO>>(result);
        }

        public DatasetRegisterDTO Create(DatasetRegisterDTO datasetEntity)
        {
            _repository.Save(_mapper.Map<DatasetEntity>(datasetEntity));
            return datasetEntity;
        }

        public DatasetRegisterDTO getById(Guid guid)
        {
            return _mapper.Map<DatasetRegisterDTO>( _repository.GetById(guid));
        }

        public byte[] DownloadFileById(Guid guid)
        {
            var result = new MemoryStream();
            var dataset = _repository.GetById(guid);    
            result.Write(Encoding.ASCII.GetBytes(dataset.toFile()));
            result.Flush();
            return result.GetBuffer();    
        }
    }
}