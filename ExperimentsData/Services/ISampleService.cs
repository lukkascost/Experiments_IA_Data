using System;
using System.Collections.Generic;
using ExperimentsData.Models.DTO;

namespace ExperimentsData.Services
{
    public interface ISampleService
    {
        List<SampleListDTO> getAll(Guid datasetGuid);
        SampleRegisterDTO Create(Guid datasetGuid, SampleRegisterDTO registerDto);
        SampleRegisterDTO getById(Guid sampleId);
    }
}